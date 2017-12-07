package aoc

data class Tower(val name: String, val childs: ArrayList<Tower>, val children: List<String>, val weight: Int, var parent: Tower? = null)

fun findRoot(input: List<Tower>): Tower? {
    val towers = hashMapOf<String, Tower>()
    input.forEach { towers.put(it.name, it) }
    val parents = towers.values.filter { it.children.isNotEmpty() }.toSet()

    for (p in parents) {
        p.children.forEach { name ->
            val child = towers[name]
            child!!.parent = p
            p.childs.add(child)
        }
    }
    return towers.values.find { it.parent == null }
}

fun detectUnbalanced(root: Tower): Int {
    var weight = root.weight
    val weights = hashMapOf<Int, ArrayList<Int>>()
    root.childs.forEach { child ->
        val w = detectUnbalanced(child)
        if (weights.containsKey(w)) {
            weights.getValue(w).add(child.weight)
        } else {
            weights.put(w, arrayListOf(child.weight))
        }
    }

    if (weights.size > 1) {
        val wrong = weights.entries.find { it.value.size == 1 }!!
        val valid = weights.entries.find { it.value.size > 1 }!!
        val diff = valid.key - wrong.key
        print("--------------- Balance: ${wrong.value[0] + diff} ")
        System.exit(0)
    }
    if (weights.size == 1) {
        weight += weights.keys.first() * weights.values.first().size
    }

    return weight
}