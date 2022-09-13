package com.example.codelab_day_02

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    // slot
    content: @Composable () -> Unit
) {
    //
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        // 전체 높이
        var containerHeight = 0
        // 전체 폭
        var containerWidth = 0

        val itemWidth = IntArray(measurables.count()){0}

        // 측정 가능한 애들을 placeable 로 변환
        val placeables: List<Placeable> = measurables.mapIndexed { index, measurable ->
            val aPlaceable = measurable.measure(constraints)

            containerHeight += aPlaceable.height
            itemWidth[index] = aPlaceable.width

            aPlaceable
        }

        // 이전 자식 요소가 어느 y좌표에 배치되었는지 추적하기 위한 변수
        var yPosition = 0

        // maxWidth
        containerWidth = itemWidth.maxOrNull() ?: constraints.maxWidth

        // 최종 크기
        layout(containerWidth, containerHeight) {
            // 자식들을 부모 레이아웃에 배치
            placeables.forEach { placeable ->
                // 화면에서의 아이템 위치를 지정하여 배치
                placeable.placeRelative(x = 0, y = yPosition)
                // 하위요소를 배치한 y위치를 추적하여 이후 자식 배치에 활용
                yPosition += placeable.height
            }
        }
    }
}