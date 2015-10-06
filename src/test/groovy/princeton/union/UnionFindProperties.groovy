package princeton.union

import org.apache.commons.lang3.RandomUtils

class UnionFindProperties {
    static boolean isReflexive(UnionFind unionFind) {
        int index = RandomUtils.nextInt(0, unionFind.size() - 1)
        return unionFind.connected(index, index)
    }

    static boolean isSymmetric(UnionFind unionFind) {
        int pIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        int qIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        unionFind.union(pIndex, qIndex)
        return unionFind.connected(pIndex, qIndex) && unionFind.connected(qIndex, pIndex)
    }

    static boolean isTransitive(UnionFind unionFind) {
        int pIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        int qIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        int rIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        unionFind.union(pIndex, qIndex)
        unionFind.union(qIndex, rIndex)
        return unionFind.connected(pIndex, rIndex)
    }
    static boolean isIdempotent(UnionFind unionFind) {
        int pIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        int qIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        unionFind.union(pIndex, qIndex)
        unionFind.union(pIndex, qIndex)
        return unionFind.connected(pIndex, qIndex)
    }
}
