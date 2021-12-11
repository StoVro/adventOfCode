package helpers

fun getXYDirections() : Array<Direction> {
    return arrayOf(Direction(-1,0), Direction(0,1), Direction(1,0), Direction(0,-1))
}

fun getAllDirections() : Array<Direction> {
    return arrayOf(Direction(-1,0),
            Direction(0,1),
            Direction(1,0),
            Direction(0,-1),
            Direction(1,1),
            Direction(1,-1),
            Direction(-1,1),
            Direction(-1,-1)
    )
}

data class Direction(val horizontalChange: Int, val verticalChange: Int)
data class Point(val y: Int, val x: Int)