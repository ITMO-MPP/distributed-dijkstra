package dijkstra.system.executor

import dijkstra.graph.Graph
import dijkstra.ProcessImpl
import dijkstra.system.environment.EnvironmentImpl
import dijkstra.system.runtime.Runtime

class SystemExecutor(private val graph: Graph, private val runtimeMaker: () -> Runtime) {
    fun execute(sId: Int): List<Long?> {
        val runtime = runtimeMaker()
        val nProc = graph.graph.size
        val processes = (0 until nProc).map {
            val curEnv = EnvironmentImpl(graph = graph, pid = it, runtime = runtime)
            ProcessImpl(environment = curEnv)
        }

        processes[sId].startComputation()
        while (true) {
            val curMsg = runtime.getMessageToPass()
            if (runtime.isFinished() || curMsg == null) {
                break
            }
            processes[curMsg.dstId].onMessage(srcId = curMsg.srcId, message = curMsg.message)
        }

        assert(runtime.isFinished())
        return processes.map { it.getDistance() }
    }
}