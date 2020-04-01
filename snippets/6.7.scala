-case (true, true) => sortedLeft(leftIndex) < sortedRight(rightIndex)
+case (true, true) => Ordering[T].lt(sortedLeft(leftIndex), sortedRight(rightIndex))
