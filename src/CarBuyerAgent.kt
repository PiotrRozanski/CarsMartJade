import jade.core.AID
import jade.core.Agent
import jade.core.behaviours.Behaviour
import jade.lang.acl.MessageTemplate


class CarBuyerAgent : Agent() {

    private lateinit var targetCarName: String
    private lateinit var sellerAgent: Array<AID>

    override fun setup() {

    }

    override fun takeDown() {
        // Printout a dismissal message
        println("Buyer-agent " + aid.name + " terminating.")
    }

    inner class RequestPerform : Behaviour() {
        private val bestSeller: AID? = null // The agent who provides the best offer
        private val bestPrice: Int = 0  // The best offered price
        private val repliesCnt = 0 // The counter of replies from seller agents
        private val mt: MessageTemplate? = null // The template to receive replies
        private val step = 0

        override fun action() {

        }

        override fun done(): Boolean {
            if (step == 2 && bestSeller == null) {
                System.out.println("Attempt failed: $targetCarName not available for sale")
            }
            return ((step == 2 && bestSeller == null) || step == 4)
        }

    }
}