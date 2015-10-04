package princeton.union

import org.apache.commons.lang3.RandomUtils
import spock.lang.Specification

class UnionFindTest extends Specification {

    def 'union works'() {
        given:
          def unionFind = new UnionFind(RandomUtils.nextInt(1, 1000))
          def q = RandomUtils.nextInt(0, unionFind.size() - 1)
          def p = RandomUtils.nextInt(0, unionFind.size() - 1)
        when:
          unionFind.union(q, p)
        then:
          reflexiveProperty(unionFind)
          symmetricProperty(unionFind)
          transitiveProperty(unionFind)
    }

    boolean reflexiveProperty(UnionFind unionFind) {
        int index = RandomUtils.nextInt(0, unionFind.size() - 1)
        return unionFind.connected(index, index)
    }

    boolean symmetricProperty(UnionFind unionFind) {
        int pIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        int qIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        unionFind.union(pIndex, qIndex)
        return unionFind.connected(pIndex, qIndex) && unionFind.connected(qIndex, pIndex)
    }

    boolean transitiveProperty(UnionFind unionFind) {
        int pIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        int qIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        int rIndex = RandomUtils.nextInt(0, unionFind.size() - 1)
        unionFind.union(pIndex, qIndex)
        unionFind.union(qIndex, rIndex)
        return unionFind.connected(pIndex, rIndex)
    }
}
