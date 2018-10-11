
import jade.core.Agent
import jade.core.behaviours.CyclicBehaviour
import jade.core.behaviours.OneShotBehaviour
import jade.lang.acl.ACLMessage
import jade.lang.acl.MessageTemplate


class CarSellerAgent : Agent() {

    private val catalogue: ArrayList<Car> = ArrayList()
    private val gui: CarSellerGUI = CarSellerGUI(this)
    private val budget: Float = 100000F

    override fun setup() {
        super.setup()
        gui.showGui()
    }

    override fun takeDown() {
        super.takeDown()
    }

    fun updateCatalogue(
        brand: String,
        model: String,
        bodyType: String,
        engineType: String,
        fuelTankCapacity: Int,
        manufactureYear: Int,
        price: Float,
        CostOfAdditionalFees: Float
    ) {
        addBehaviour(object : OneShotBehaviour() {
            override fun action() {
                catalogue.add(
                    Car(
                        brand,
                        model,
                        bodyType,
                        engineType,
                        fuelTankCapacity,
                        manufactureYear,
                        price,
                        CostOfAdditionalFees
                    )
                )
                println("$brand $model inserted into catalogue. Price = $price")
            }
        })
    }

    private inner class OfferRequestsServer : CyclicBehaviour() {
        override fun action() {
            val mt = MessageTemplate.MatchPerformative(ACLMessage.CFP)
            val msg = myAgent.receive(mt)
            if (msg != null) {

                val title = msg.content
                val reply = msg.createReply()

                reply.performative = ACLMessage.PROPOSE
                reply.content = "1000"
                myAgent.send(reply)
            } else {
                block()
            }
        }
    }

    private inner class PurchaseOrdersServer : CyclicBehaviour() {
        override fun action() {
            val mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL)
            val msg = myAgent.receive(mt)
            if (msg != null) {
                // ACCEPT_PROPOSAL Message received. Process it
                val title = msg.content
                val reply = msg.createReply()

                reply.performative = ACLMessage.INFORM
                println(title + " sold to agent " + msg.sender.name)
                myAgent.send(reply)
            } else {
                block()
            }
        }
    }
}