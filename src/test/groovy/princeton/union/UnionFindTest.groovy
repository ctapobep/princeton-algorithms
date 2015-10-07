package princeton.union

import spock.lang.Specification

import static org.apache.commons.lang3.RandomUtils.nextInt
import static princeton.union.UnionFindProperties.isIdempotent
import static princeton.union.UnionFindProperties.isReflexive
import static princeton.union.UnionFindProperties.isSymmetric
import static princeton.union.UnionFindProperties.isTransitive
import static princeton.union.UnionFindProperties.sizeOfParentIsTwiceAsLargeAsAnyOfItsSubTrees

class UnionFindTest extends Specification {

    def 'Quick Union Example'() {
        setup:
          def sut = new QuickUnionFind((0..9).toArray() as int[])
          for (int[] pair : unions) {
              sut.union(pair[0], pair[1])
          }
        expect:
          sut.array == expectedResult as int[]
        where:
          unions                                           | expectedResult
          [[9, 4], [2, 4], [3, 5], [1, 2], [8, 1], [0, 3]] | [3, 2, 4, 5, 4, 5, 6, 7, 1, 4]
          [[3, 7], [6, 7], [0, 9], [2, 4], [8, 7], [6, 5]] | [9, 1, 4, 7, 4, 5, 7, 5, 7, 9]
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
          sizeOfParentIsTwiceAsLargeAsAnyOfItsSubTrees(unionFind)
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
          sizeOfParentIsTwiceAsLargeAsAnyOfItsSubTrees(unionFind)
    }
}
