package princeton.union
/**
 * Since we're touching elements when we're looking for their root, why not change their parent to the tree root?
 * Though instead of pointing directly to the root (for that we'd need additional loop) we can point to the grand parent
 * and half the tree depth.
 */
class WeighedQuickUnionWithPathCompression extends WeighedQuickUnion {
    WeighedQuickUnionWithPathCompression(int size) {
        super(size)
    }

    private WeighedQuickUnionWithPathCompression(int[] array, int[] sizes) {
        super(array, sizes)
    }

    @Override
    WeighedQuickUnionWithPathCompression union(int p, int q) {
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
        return new WeighedQuickUnionWithPathCompression(this.array)
    }

    @Override
    boolean connected(int p, int q) {
        int pRoot = root(p)
        int qRoot = root(q)
        return pRoot == qRoot
    }

    private int root(int index) {
        while (index != array[index]) {
            array[index] = array[array[index]]
            index = array[index]
        }
        index
    }
}
