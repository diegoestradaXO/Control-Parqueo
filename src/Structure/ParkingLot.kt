package Structure

class ParkingLot(
        val name: String,
        var x: Int,
        var y:Int,
        var isOccupied: Boolean= false,
        var licensePlate: String= ""
){
    fun park(plate: String){
        isOccupied= true
        licensePlate = plate
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