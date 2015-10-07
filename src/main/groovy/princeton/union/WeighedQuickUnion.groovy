package princeton.union

class WeighedQuickUnion implements UnionFind {
    int[] array
    int[] sizes

    WeighedQuickUnion(int size) {
        this((0..(size-1)).toArray([]) as int[], [1] * size as int[])
    }

    WeighedQuickUnion(int[] array, int[] sizes) {
        this.array = array
        if (!sizes) {
            sizes = new int[array.length]
            Arrays.fill(sizes, 1)
        }
        this.sizes = sizes
        assert this.sizes.length == this.array.length
    }

    @Override
    WeighedQuickUnion union(int p, int q) {
        assert p < array.length
        assert q < array.length
        if (p == q) {
            return this
        }
        int pRoot = root(p)
        int qRoot = root(q)
        if (pRoot == qRoot) {
            return this;
        }
        if (sizes[pRoot] < sizes[qRoot]) {
            array[pRoot] = qRoot
            sizes[qRoot] += sizes[pRoot]
        } else {
            array[qRoot] = pRoot
            sizes[pRoot] += sizes[qRoot]
        }
        return new WeighedQuickUnion(this.array, this.sizes)
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
