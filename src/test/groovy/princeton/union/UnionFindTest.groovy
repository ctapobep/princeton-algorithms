package princeton.union

import spock.lang.Specification

import static org.apache.commons.lang3.RandomUtils.nextInt
import static princeton.union.UnionFindProperties.isIdempotent
import static princeton.union.UnionFindProperties.isReflexive
import static princeton.union.UnionFindProperties.isSymmetric
import static princeton.union.UnionFindProperties.isTransitive

class UnionFindTest extends Specification {

    def 'Quick Union Example'() {
        given:
          def unionFind = new QuickUnionFind((0..9).toArray() as int[])
        when:
          def union = unionFind.union(9, 4).union(2, 4).union(3, 5).union(1, 2).union(8, 1).union(0, 3)
        then:
          union.array == [3, 2, 4, 5, 4, 5, 6, 7, 1, 4] as int[]
    }

    def 'Weighed Quick Union Example'() {
        given:
          def unionFind = new WeighedQuickUnionFind((0..9).toArray() as int[])
        when:
          def union = unionFind.union(8, 1).union(4, 3).union(6, 2).union(4, 5).union(7, 9)
                  .union(5, 8).union(7, 2).union(6, 5).union(8, 0)
        then:
          union.array == [4, 8, 6, 4, 4, 4, 7, 4, 4, 7] as int[]
    }

    def 'quick union'() {
        given:
          def unionFind = new QuickUnionFind(nextInt(1, 1000))
          def q = nextInt(0, unionFind.size() - 1)
          def p = nextInt(0, unionFind.size() - 1)
        when:
          unionFind.union(q, p)
        then:
          isReflexive(unionFind)
          isSymmetric(unionFind)
          isTransitive(unionFind)
          isIdempotent(unionFind)
    }

    def 'weighed quick union'() {
        given:
          def unionFind = new WeighedQuickUnionFind(nextInt(1, 1000))
          def q = nextInt(0, unionFind.size() - 1)
          def p = nextInt(0, unionFind.size() - 1)
        when:
          unionFind.union(q, p)
        then:
          isReflexive(unionFind)
          isSymmetric(unionFind)
          isTransitive(unionFind)
          isIdempotent(unionFind)
    }

    def 'weighed quick union with path compression'() {
        given:
          def unionFind = new WeighedQuickUnionFindWithPathCompression(nextInt(1, 1000))
          def q = nextInt(0, unionFind.size() - 1)
          def p = nextInt(0, unionFind.size() - 1)
        when:
          unionFind.union(q, p)
        then:
          isReflexive(unionFind)
          isSymmetric(unionFind)
          isTransitive(unionFind)
          isIdempotent(unionFind)
    }
}
