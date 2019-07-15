import scala.meta._

object ScalametaTransformer {

  def transform(input: String): String = {
    val tree = input.parse[Source].get

    val isMain: Mod => Boolean = { case mod"@main" => true; case _ => false }

    val transformer = new Transformer {
      override def apply(tree: Tree): Tree = tree match {
        case q"..$mods object $ename extends { ..$stats } with ..$inits { $self => ..$stats1 }" if mods.exists(isMain)  =>
          q"""
            ..${mods.filterNot(isMain)} object $ename extends { ..$stats } with ..$inits { $self =>
              def main(args: Array[String]): Unit = {
                ..$stats1
              }
            }"""

        case node => super.apply(node)
      }
    }
    
    transformer(tree).toString
  }
  
}
