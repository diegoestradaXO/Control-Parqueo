package Structure

import java.io.File
import java.io.InputStream

class Stage(
        val name: String,
        val id: String,
        val color: String,
        val path: String,
        var width: Int=0,
        var height: Int=0,
        private var parkingLots: ArrayList<ParkingLot> = ArrayList(),
        private var walls: ArrayList<Wall> = ArrayList()

){
    fun readMap(location:String):ArrayList<String>{
        var StageArrayList= ArrayList<String>()
        val inputStream: InputStream = File(location).inputStream()
        val lineList= mutableListOf<String>()
        inputStream.bufferedReader().useLines { lines-> lines.forEach { lineList.add(it) } }
        lineList.forEach{StageArrayList.add(it)}
        return StageArrayList
    }

    fun createMap(readMap: ArrayList<String>): Boolean {
        var created =true
        var checking= ArrayList<String>()
        this.height = readMap.size
        this.width = readMap.get(0).length
        for (row in readMap.indices) {
            for (column in readMap.get(0).indices) {
                var toEvaluate = readMap.get(row)[column].toString()
                if (toEvaluate == "*") {
                    var newWall = Wall(column, row)
                    walls.add(newWall)
                } else if (toEvaluate.toIntOrNull() != null) {
                    var newParkingSpot = ParkingLot(name = toEvaluate, x = column, y = row)
                    parkingLots.add(newParkingSpot)
                    checking.add(toEvaluate)
                } else if(toEvaluate!=" "){
                    var anotherParkingSpot = ParkingLot(name = toEvaluate, x = column, y = row)
                    parkingLots.add(anotherParkingSpot)
                    checking.add(toEvaluate)
                }
            }
            if (checking.distinct().size< checking.size){
                created=false
            }
        }
        return created
    }
    fun getWall(X: Int,Y: Int):Wall?{
        for(wall in walls){
            if (wall.X==X && wall.Y== Y){
                return wall
            }
        }
        return null
    }
    fun hasWall(X: Int, Y:Int): Boolean{
        for(wall in walls){
            if(wall.X==X && wall.Y== Y){
                return true
            }
        }
        return false
    }
    fun getParkingLot(posX: Int,posY: Int):ParkingLot?{
        for(ParkingSpot in parkingLots){
            if (ParkingSpot.x==posX && ParkingSpot.y== posY){
                return ParkingSpot
            }
        }
        return null
    }
    fun hasParkingLot(posX: Int, posY:Int): Boolean{
        for(ParkingSpot in parkingLots){
            if(ParkingSpot.x==posX && ParkingSpot.y== posY){
                return true
            }
        }
        return false
    }
    fun findParkingSpot(name: String): ParkingLot?{
        val selectedParkingLot = parkingLots.filter { it.name == name}
        if (selectedParkingLot.count()> 0){
            return selectedParkingLot[0]
        }
        return null
    }

}