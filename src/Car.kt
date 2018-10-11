data class Car(val brand: String,
               val model: String,
               val bodyType: String,
               val engineType: String,
               val fuelTankCapacity: Int,
               val manufactureYear: Int,
               val price: Float,
               val CostOfAdditionalFees: Float) {

    private var totalCost: Float = price + CostOfAdditionalFees
}