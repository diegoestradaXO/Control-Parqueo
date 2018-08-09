package Structure

import java.io.File
import Structure.Stage
import java.io.InputStream

class Building(
        val stages: ArrayList<Stage> = ArrayList()
){

    fun deleteStage(stageID: String): Boolean{
        val selectedStage = stages.filter {it.id == stageID}
        if(selectedStage.count() > 0){
            stages.remove(selectedStage[0])
            return true
        }else{
            return false
        }
    }
    fun addStage(stage: Stage): Boolean{
        if ( checkStageID(stage.id)== null && checkStagName(stage.name)== null && checkStageColor(stage.color)== null ){
            stages.add(stage)
            return true//si se agrego
        }
        return false//no se agrego
    }

    fun checkStageColor(color: String):Stage?{
        val selectedColor = stages.filter { it.color == color }
        if(selectedColor.count() > 0) return selectedColor[0]
        else{ return null}
    }
    fun checkStageID(id: String):Stage?{
        val selectedID = stages.filter { it.id == id }
        if(selectedID.count() > 0) return selectedID[0]
        else{ return null}
    }
    fun checkStagName(name: String):Stage?{
        val selectedName = stages.filter { it.name == name }
        if(selectedName.count() > 0) return selectedName[0]
        else{ return null}
    }
    fun getLevelbyPlate(plate: String):Stage?{
        for (stage in stages){
            if (stage.checkIfPilotIsAlreadyParked(plate)!=null){
                return stage
            }
        }
        return null
    }
    fun getAvailableStages():ArrayList<Stage> {
        val availableStages = ArrayList<Stage>()
        for (stage in stages) {
            var isAvailable = false
            for (parkingLot in stage.parkingLots) {
                if (!parkingLot.isOccupied) {
                    isAvailable = !isAvailable

                }

            }
            availableStages.add(stage)

        }
        return availableStages
    }



    override fun toString(): String {
        var levelsToStr = ""
        stages .forEach {
            levelsToStr += "ID del nivel: ${it.id} , Nombre: ${it.name}, Color: ${it.color}\n"
        }
        return levelsToStr
    }
}