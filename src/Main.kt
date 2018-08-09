import Structure.Building
import Structure.Stage

fun main(args: Array<String>) {
    var wantsToContinue = true
    val myBuilding = Building()
    do{
        println("""
                Escoja una opcion:
                1.Administrador
                2.Conductor
                3.Salir:
        """.trimIndent())
        val option1 = readLine()!!.toInt()
        if(0 < option1 && option1 < 4){
            if (option1 == 1) {
                var admin = true
                do {
                    println("""
                    Escoja una opcion:
                    1.Crear nivel
                    2.Eliminar nivel
                    3.Ver todos los niveles
                    4.Salir:
                """.trimIndent())
                    val option2 = readLine()!!.toInt()
                    if (option2 > 0 && option2 < 5) {
                        if (option2 == 1) {
                            println("Ingrese el nombre del nivel:")
                            val stageName = readLine()!!.toString()
                            println("Ingrese el identificador:")
                            val stageID = readLine()!!.toString()
                            println("Ingrese el color del nivel:")
                            val stageColor = readLine()!!.toString()
                            println("Ingrese la ruta del archivo del Nivel:")
                            val stagePath = readLine()!!.toString()
                            if (myBuilding.checkStageColor(stageColor) != null || myBuilding.checkStageID(stageID) != null || myBuilding.checkStagName(stageName) != null) {
                                println("Lo siento, pero ya existe un nivel con alguno de los datos que ingresaste")
                            }
                            var stageToCreate = Stage(name = stageName, id = stageID, color = stageColor, path = stagePath)
                            if (!stageToCreate.createMap(stageToCreate.readMap(stagePath))) {
                                println("El mapa que ingresaste contiene parqueos iguales, asegurate de que todos sean diferentes")
                            } else {
                                myBuilding.addStage(stageToCreate)
                                println("Felicidades, el nivel ha sido creado satisfactoriamente")
                            }
                        }
                        if (option2 == 2) {
                            val id = readLine()!!.toString()
                            val deleting = myBuilding.deleteStage(id)
                            if (deleting){
                                println("Nivel borrado con exito...")
                            }else{
                                println("No se ha encontrado el mapa con el ID ingresado...")
                            }
                        }
                        if (option2 == 3) {
                            println(myBuilding)
                        }
                        if (option2 == 4) {
                            admin = !admin
                        }
                        } else {
                        println("Opcion invalida...Intentelo de nuevo...")
                    }
                }while (admin)
            }
            if(option1 == 2){
                println("Bienvenido conductor")
                var conductor = true
                do{
                    println("""
                    Escoja una opcion:
                    1.Ingresar placa
                    2.Salir:
                """.trimIndent())
                    val option3 = readLine()!!.toInt()
                    if (option3 > 0 && option3 < 3) {
                        if (option3 == 1){
                            val licensePlate = readLine().toString()
                            if (myBuilding.getLevelbyPlate(plate = licensePlate)!=null){
                                println(myBuilding.getLevelbyPlate(licensePlate))
                                println(myBuilding.getLevelbyPlate(licensePlate)!!.stageToString())
                            }else{
                                for(stage in myBuilding.getAvailableStages()){
                                    println("Estos son los niveles con espacios disponibles...")
                                    println(stage)
                                }
                                println("Escoja el nivel en el que desea parquear su vehiculo(Ingresar el id)")
                                val stageId = readLine()!!.toString()
                                val stageChosed = myBuilding.checkStageID(stageId)
                                if (stageChosed != null){
                                    println(stageChosed.stageToString())
                                    println("Ingrese el nombre del parqueo en el que desea estacionarse: ")
                                    val parkingLotId = readLine()!!.toString()
                                    if (stageChosed.getParkingLot(parkingLotId) != null){
                                        val mypark = stageChosed.getParkingLot(parkingLotId)
                                        mypark.park(plate = licensePlate)


                                    }

                                }else{
                                    println("Lo siento, pero el id que ingresaste no concuerda con ningun parqueo disponible..")
                                }
                            }


                        }
                        if (option3 ==2 ){
                            conductor = !conductor
                        }
                    }
                    else{
                        println("Opcion invalida, intentalo de nuevo")}
                }while (conductor)
            }
            if(option1 == 3){
                 wantsToContinue = false
            }


        }else{
            println("Opcion invalida, intente de nuevo...")}

    }while (wantsToContinue)
    }

///Users/Diego/Desktop/mapa.txt