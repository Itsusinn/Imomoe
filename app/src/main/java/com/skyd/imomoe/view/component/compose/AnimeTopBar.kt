package com.skyd.imomoe.view.component.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skyd.imomoe.R

enum class AnimeTopBarStyle {
    Small, Large
}

@Composable
fun AnimeTopBar(
    modifier: Modifier = Modifier,
    style: AnimeTopBarStyle = AnimeTopBarStyle.Small,
    title: @Composable () -> Unit,
    contentPadding: PaddingValues = WindowInsets.statusBars.asPaddingValues(),
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val colors = when (style) {
        AnimeTopBarStyle.Small -> TopAppBarDefaults.smallTopAppBarColors()
        AnimeTopBarStyle.Large -> TopAppBarDefaults.largeTopAppBarColors()
    }
    val scrollFraction = scrollBehavior?.scrollFraction ?: 0f
    val appBarContainerColor by colors.containerColor(scrollFraction)

    Surface(modifier = modifier, color = appBarContainerColor) {
        when (style) {
            AnimeTopBarStyle.Small -> {
                SmallTopAppBar(
                    modifier = Modifier.padding(contentPadding),
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = actions,
                    colors = colors,
                    scrollBehavior = scrollBehavior
                )
            }
            AnimeTopBarStyle.Large -> {
                LargeTopAppBar(
                    modifier = Modifier.padding(contentPadding),
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = actions,
                    colors = colors,
                    scrollBehavior = scrollBehavior
                )
            }
        }
    }
}

@Composable
fun TopBarIcon(
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    tint: Color = LocalContentColor.current,
    contentDescription: String?,
) {
    IconButton(onClick = onClick) {
        Icon(
            modifier = modifier.size(24.dp),
            painter = painter,
            tint = tint,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun BackIcon(onClick: () -> Unit = {}) {
    TopBarIcon(
        painter = painterResource(id = R.drawable.ic_arrow_back_24),
        contentDescription = null,
        onClick = { onClick() }
    )
}
