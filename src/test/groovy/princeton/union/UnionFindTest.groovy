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
          def sut = connectPairs(new QuickUnionFind(10), pairs)
        expect:
          sut.array == expectedResult as int[]
        where:
          pairs                                            | expectedResult
          [[9, 4], [2, 4], [3, 5], [1, 2], [8, 1], [0, 3]] | [3, 2, 4, 5, 4, 5, 6, 7, 1, 4]
          [[3, 7], [6, 7], [0, 9], [2, 4], [8, 7], [6, 5]] | [9, 1, 4, 7, 4, 5, 7, 5, 7, 9]
    }

    def 'Weighed Quick Union Example'() {
        setup:
          def sut = connectPairs(new WeighedQuickUnionFind(10), pairs)
        expect:
          sut.array == expectedResult as int[]
        where:
          pairs                                                                    | expectedResult
          [[8, 1], [4, 3], [6, 2], [4, 5], [7, 9], [5, 8], [7, 2], [6, 5], [8, 0]] | [4, 8, 6, 4, 4, 4, 7, 4, 4, 7]
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

    private static <T extends UnionFind> T connectPairs(T unionFind, List<List<Integer>> pairs) {
        UnionFind result = unionFind
        for (int[] pair : pairs) {
            result = result.union(pair[0], pair[1])
        }
        return unionFind
    }
}
