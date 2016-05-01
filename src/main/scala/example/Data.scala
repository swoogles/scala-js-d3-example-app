package bill.d3
import scala.scalajs.js

trait TreeData {
  val treeData = """
  {
    "name": "Animal",
    "children": [
    {
      "name": "Vertebrates",
      "children": [
      {
        "name": "Mammals",
        "children": [
        { "name": "Canine" },
        { "name": "Feline" },
        { "name": "Primate" }
        ]
      },
      { "name": "Fish" },
      { "name": "Birds" }
      ]
    },
    {
      "name": "Invertebrates",
      "children": [
      { "name": "Jellyfish" },
      { "name": "Worms" }

      ]
    }
    ]
  }
  """

  val treeDataJson =
    js.JSON.parse(
      treeData
    )

}