// MergeSort.sc
def mergeSort(items: Array[Int]): Array[Int] = {
  if (items.length <= 1) items
  else {
    val (left, right) = items.splitAt(items.length / 2)
    val sortedLeft = mergeSort(left)
    val sortedRight = mergeSort(right)
    var leftIdx = 0
    var rightIdx = 0
    val output = Array.newBuilder[Int]
    while(leftIdx < sortedLeft.length || rightIdx < sortedRight.length){
      val takeLeft = (leftIdx < sortedLeft.length, rightIdx < sortedRight.length) match{
        case (true, false) => true
        case (false, true) => false
        case (true, true) => sortedLeft(leftIdx) < sortedRight(rightIdx)
      }
      if (takeLeft){
        output += sortedLeft(leftIdx)
        leftIdx += 1
      }else{
        output += sortedRight(rightIdx)
        rightIdx += 1
      }
    }
    output.result()
  }
}
// Usage
val input = Array(8, 3, 5, 4, 6, 1, 0, 2, 7, 9)
pprint.log(input)
assert(pprint.log(mergeSort(input)).sameElements(Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)))
