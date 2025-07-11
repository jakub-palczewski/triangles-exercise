package triangles.exercise.service.pathfinder

import cats.Id
import triangles.exercise.model.{Path, Triangle}

class PathFinderImpl extends PathFinder[Id]:


  def findMinPath(triangle: Triangle): Path = {
    
    val initialSums = triangle.rows.last.zipWithIndex.map { case (value, idx) =>
      (value, Vector(idx))  
    }
    
    val minPaths = triangle.rows.init.reverse.zipWithIndex.foldLeft(initialSums) {
      case (previousRow, (currentRow, reversedRowIndex)) =>
        val actualRowIndex = triangle.rows.length - 2 - reversedRowIndex

        currentRow.zipWithIndex.map { case (value, idx) =>
          val (leftSum, leftPath) = previousRow(idx)
          val (rightSum, rightPath) = previousRow(idx + 1)

          if (leftSum <= rightSum) {
            (value + leftSum, idx +: leftPath)  // Prepend to Vector
          } else {
            (value + rightSum, idx +: rightPath)  // Prepend to Vector
          }
        }
    }
    
    val (minSum, pathIndices) = minPaths.head

    
    val values = pathIndices.zipWithIndex.map { case (colIdx, rowIdx) =>
      triangle.rows(rowIdx)(colIdx)
    }

    Path(values) 
  }
