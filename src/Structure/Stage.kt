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
        val parkingLots: ArrayList<ParkingLot> = ArrayList(),
        private val walls: ArrayList<Wall> = ArrayList()

){
    fun readMap(path:String):ArrayList<String>{
        var StageArrayList= ArrayList<String>()
        val inputStream: InputStream = File(path).inputStream()
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
                    var newParkingLot = ParkingLot(name = toEvaluate, x = column, y = row)
                    parkingLots.add(newParkingLot)
                    checking.add(toEvaluate)
                } else if(toEvaluate!=" "){
                    var secParkingLot = ParkingLot(name = toEvaluate, x = column, y = row)
                    parkingLots.add(secParkingLot)
                    checking.add(toEvaluate)
                }
            }
            if (checking.distinct().size< checking.size){
                created=false
            }
        }
        return created
    }
    fun stageToString():String{
        var str=""
        for(row in 0..height){
            for (Column in 0..width){
                if(hasParkingLot(Column,row)){
                    var newParkingLot= getParkingLot(Column,row)
                    str+=newParkingLot
                }
                else if (hasWall(Column,row)){
                    var NewWall= getWall(Column,row)
                    str+=NewWall
                }
                else{
                    str+=" "
                }
            }
            str+="\n"
        }
        return str
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
    fun getParkingLot(name: String): ParkingLot?{
        val selectedParkingLot = parkingLots.filter { it.name == name}
        if (selectedParkingLot.count()> 0){
            return selectedParkingLot[0]
        }
        return null
    }
    fun checkIfPilotIsAlreadyParked(plate: String):ParkingLot?{
        val selectedParkingLotbyPlate = parkingLots.filter { it.licensePlate == plate }
        if (selectedParkingLotbyPlate.count()>0){
            return selectedParkingLotbyPlate[0]
        }
        return null
    }
    override fun toString(): String {
        return """
            Nivel:
                Name: $name
                Id: $id
                Color : $color
                """.trimIndent()
    }


}