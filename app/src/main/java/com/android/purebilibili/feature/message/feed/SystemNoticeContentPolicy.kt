package com.android.purebilibili.feature.message.feed

internal data class SystemNoticeContentSegment(
    val text: String,
    val link: String? = null
)

private val systemNoticePlaceholderRegex = Regex("#\\{([^}]*)}\\{([^}]*)}")
private val systemNoticePlainLinkRegex = Regex(
    "(https?://[^\\s]+|www\\.[^\\s]+|BV[a-zA-Z0-9]{10}|av\\d+)",
    setOf(RegexOption.IGNORE_CASE)
)

internal fun parseSystemNoticeContentSegments(content: String): List<SystemNoticeContentSegment> {
    if (content.isEmpty()) return emptyList()

    val segments = mutableListOf<SystemNoticeContentSegment>()
    var lastIndex = 0
    systemNoticePlaceholderRegex.findAll(content).forEach { match ->
        if (match.range.first > lastIndex) {
            appendSystemNoticePlainSegments(
                target = segments,
                text = content.substring(lastIndex, match.range.first)
            )
        }

        val label = match.groupValues[1].trim()
        val link = normalizeSystemNoticeLink(match.groupValues[2])
        if (label.isNotEmpty()) {
            segments += SystemNoticeContentSegment(
                text = label,
                link = link.takeIf { it.isNotBlank() }
            )
        }
        lastIndex = match.range.last + 1
    }

    if (lastIndex < content.length) {
        appendSystemNoticePlainSegments(
            target = segments,
            text = content.substring(lastIndex)
        )
    }
    return segments
}

private fun appendSystemNoticePlainSegments(
    target: MutableList<SystemNoticeContentSegment>,
    text: String
) {
    var lastIndex = 0
    systemNoticePlainLinkRegex.findAll(text).forEach { match ->
        if (match.range.first > lastIndex) {
            target += SystemNoticeContentSegment(text.substring(lastIndex, match.range.first))
        }

        val rawLink = match.value
        target += SystemNoticeContentSegment(
            text = rawLink,
            link = normalizeSystemNoticeLink(rawLink)
        )
        lastIndex = match.range.last + 1
    }

    if (lastIndex < text.length) {
        target += SystemNoticeContentSegment(text.substring(lastIndex))
    }
}

private fun normalizeSystemNoticeLink(rawLink: String): String {
    val unquoted = rawLink.trim()
        .removeSurrounding("\"")
        .replace("\\/", "/")
        .replace("\\u0026", "&")
    return when {
        unquoted.startsWith("www.", ignoreCase = true) -> "https://$unquoted"
        unquoted.startsWith("BV", ignoreCase = true) -> "https://www.bilibili.com/video/$unquoted"
        unquoted.startsWith("av", ignoreCase = true) -> "https://www.bilibili.com/video/$unquoted"
        else -> unquoted
    }
}
