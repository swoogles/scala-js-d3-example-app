package bill.d3

import scala.scalajs.js
import scala.scalajs.js.Dynamic

trait TreeData {
  val treeData =
    """
{
  "name": "Animal",
  "children": [
    {
      "name": "Vertebrates",
      "parent": "Animal",
      "children": [
        {
          "name": "Mammals",
          "children": [
            {
              "name": "Canine",
              "parent": "Mammals"
            },
            {
              "name": "Primate" ,
              "parent": "Mammals"
            }
          ]
        },
        {
          "name": "Birds",
          "parent": "Vertebrates"
        }
      ]
    },
    {
      "name": "Invertebrates",
      "parent": "Animal",
      "children": [
        {
          "parent" :"Invertebrates",
          "name": "Jellyfish"
        },
        {
          "parent" :"Invertebrates",
          "name": "Worms"
        }

      ]
    }
  ]
}

    """

  val jsonDynamicFromTrait: Dynamic =
    js.JSON.parse(
      treeData
    )

}