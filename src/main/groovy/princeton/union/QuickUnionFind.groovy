package princeton.union

class QuickUnionFind implements UnionFind {
    int[] array

    QuickUnionFind(int size) {
        this.array = (0..size).collect{it}
    }
    QuickUnionFind(int[] array) {
        this.array = array;
    }

    @Override
    QuickUnionFind union(int p, int q) {
        assert p < this.array.size()
        assert q < this.array.size()
        if(p == q) {
            return this
        }
        int index = root(p)
        array[index] = q
        return new QuickUnionFind(this.array)
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
        index
    }

    @Override
    int size() { return array.size() }
}
