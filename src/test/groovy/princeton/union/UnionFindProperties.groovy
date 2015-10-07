package princeton.union

import static org.apache.commons.lang3.RandomUtils.nextInt

class UnionFindProperties {
    static boolean isReflexive(UnionFind unionFind) {
        int index = nextInt(0, unionFind.size() - 1)
        return unionFind.connected(index, index)
    }

    static boolean isSymmetric(UnionFind unionFind) {
        int pIndex = nextInt(0, unionFind.size() - 1)
        int qIndex = nextInt(0, unionFind.size() - 1)
        unionFind.union(pIndex, qIndex)
        return unionFind.connected(pIndex, qIndex) && unionFind.connected(qIndex, pIndex)
    }

    static boolean isTransitive(UnionFind unionFind) {
        int pIndex = nextInt(0, unionFind.size() - 1)
        int qIndex = nextInt(0, unionFind.size() - 1)
        int rIndex = nextInt(0, unionFind.size() - 1)
        unionFind.union(pIndex, qIndex)
        unionFind.union(qIndex, rIndex)
        return unionFind.connected(pIndex, rIndex)
    }

    static boolean isIdempotent(UnionFind unionFind) {
        int pIndex = nextInt(0, unionFind.size() - 1)
        int qIndex = nextInt(0, unionFind.size() - 1)
        unionFind.union(pIndex, qIndex)
        unionFind.union(pIndex, qIndex)
        return unionFind.connected(pIndex, qIndex)
    }

    /**
     * True for algorithms with Lg complexity of find operation (Weighed quick tree). Sub-tree is added to another
     * parent only if that parent is at least equal in size. And when we added that sub-tree the parent become even
     * larger (at least 2 times) comparing to the sub-tree.
     *
     **/
    static boolean sizeOfParentIsTwiceAsLargeAsAnyOfItsSubTrees(WeighedQuickUnion unionFind) {
        def size = unionFind.size()
        (0..nextInt(0, 1000)).each {
            unionFind.union(nextInt(0, size), nextInt(0, size))
        }
        for (int i = 0; i < size; i++) {
            int treeSize = unionFind.sizes[i]
            def parentTreeSize = unionFind.sizes[unionFind.array[i]]
            if(treeSize == parentTreeSize) {
                continue
            }
            if (!(treeSize * 2 <= parentTreeSize)) {
                return false
            }
        }
        return true
    }
}
