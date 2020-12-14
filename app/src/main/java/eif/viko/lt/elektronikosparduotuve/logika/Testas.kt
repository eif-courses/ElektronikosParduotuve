package eif.viko.lt.elektronikosparduotuve.logika

class Testas {

    companion object{
        fun dalinti() = 2/0;
        fun dauginti(a:Int, b:Int): Int {
            return a * b
        }
    }
}