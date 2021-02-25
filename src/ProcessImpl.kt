package dijkstra

import dijkstra.messages.*
import dijkstra.system.environment.Environment

class ProcessImpl(private val environment: Environment) : Process {
    override fun onMessage(srcId: Int, message: Message) {
        TODO("Not yet implemented")
    }

    override fun getDistance(): Long? {
        TODO("Not yet implemented")
    }

    override fun startComputation() {
        TODO("Not yet implemented")
    }
}