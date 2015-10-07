package princeton.union

class QuickUnion implements UnionFind {
    int[] array

    QuickUnion(int size) {
        this.array = (0..(size - 1)).toArray() as int[]
    }

    QuickUnion(int[] array) {
        this.array = array;
    }

    @Override
    QuickUnion union(int p, int q) {
        assert p < this.array.length
        assert q < this.array.length
        if(p == q) {
            return this
        }
        int pRoot = root(p)
        array[pRoot] = root(q)
        return new QuickUnion(this.array)
    }

    @Override
    boolean connected(int p, int q) {
        int pRoot = root(p)
        int qRoot = root(q)
        return pRoot == qRoot
    }

    private int root(int index) {
        while (index != array[index]) {
            index = array[index]
        }
        return index
    }

    @Override
    int size() { return array.size() }
}
