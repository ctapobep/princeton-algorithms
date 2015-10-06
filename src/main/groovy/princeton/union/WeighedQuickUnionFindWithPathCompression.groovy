package princeton.union
/**
 * Since we're touching elements when we're looking for their root, why not change their parent to the tree root?
 * Though instead of pointing directly to the root (for that we'd need additional loop) we can point to the grand parent
 * and half the tree depth.
 */
class WeighedQuickUnionFindWithPathCompression implements UnionFind {
    int[] array
    int[] sizes

    WeighedQuickUnionFindWithPathCompression(int size) {
        this.array = (0..size).collect { it }
        this.sizes = new int[size]
        Arrays.fill(sizes, 1)
    }

    private WeighedQuickUnionFindWithPathCompression(int[] array, int[] sizes) {
        this.array = array
        this.sizes = sizes
    }

    @Override
    WeighedQuickUnionFindWithPathCompression union(int p, int q) {
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
        return new WeighedQuickUnionFindWithPathCompression(this.array)
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

    @Override
    int size() { return array.size() }
}
