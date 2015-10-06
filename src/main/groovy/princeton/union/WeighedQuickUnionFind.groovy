package princeton.union

class WeighedQuickUnionFind implements UnionFind {
    int[] array
    int[] sizes

    WeighedQuickUnionFind(int size) {
        this.array = (0..size).collect { it }
        this.sizes = new int[size]
        Arrays.fill(sizes, 1)
    }

    private WeighedQuickUnionFind(int[] array, int[] sizes) {
        this.array = array
        this.sizes = sizes
    }

    @Override
    WeighedQuickUnionFind union(int p, int q) {
        if (p == q) {
            return this
        }
        int pRoot = root(p)
        int qRoot = root(q)
        if (sizes[pRoot] > sizes[qRoot]) {
            array[pRoot] = qRoot
            sizes[pRoot] += sizes[qRoot]
        } else {
            array[qRoot] = pRoot
            sizes[qRoot] += sizes[pRoot]
        }
        return new WeighedQuickUnionFind(this.array)
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
