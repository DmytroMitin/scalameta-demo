import scala.annotation.StaticAnnotation

@ann1 @main @ann2
object App {
  println("Hello!")
}

class ann1 extends StaticAnnotation
class ann2 extends StaticAnnotation
class main extends StaticAnnotation