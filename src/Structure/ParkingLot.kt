package Structure

class ParkingLot(
        val name: String,
        var x: Int,
        var y:Int,
        var isOccupied: Boolean= false
){
    fun toggle(){
        isOccupied= !isOccupied
    }

    override fun toString(): String {
        var str=""
        when(isOccupied){
            false-> str= name
            true-> str= "@"
        }
        return str
    }

}