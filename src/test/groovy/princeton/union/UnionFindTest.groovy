package princeton.union

import spock.lang.Specification

import static org.apache.commons.lang3.RandomUtils.nextInt
import static princeton.union.UnionFindProperties.isIdempotent
import static princeton.union.UnionFindProperties.isReflexive
import static princeton.union.UnionFindProperties.isSymmetric
import static princeton.union.UnionFindProperties.isTransitive
import static princeton.union.UnionFindProperties.sizeOfParentIsTwiceAsLargeAsAnyOfItsSubTrees

class UnionFindTest extends Specification {

    def 'Quick Find Example'() {
        setup:
          def sut = connectPairs(new QuickFind(10), pairs)
        expect:
          sut.array == expectedResult as int[]
        where:
          pairs                                            | expectedResult
          [[3, 7], [6, 7], [0, 9], [2, 4], [8, 7], [6, 5]] | [9, 1, 4, 5, 4, 5, 5, 5, 5, 9]
          [[9, 4], [2, 4], [3, 5], [1, 2], [8, 1], [0, 3]] | [5, 4, 4, 5, 4, 5, 6, 7, 4, 4]
          [[1, 8], [4, 3], [9, 8], [2, 9], [1, 0], [5, 3]] | [0, 0, 0, 3, 3, 3, 6, 7, 0, 0]
    }

    def 'Quick Union Example'() {
        setup:
          def sut = connectPairs(new QuickUnion(10), pairs)
        expect:
          sut.array == expectedResult as int[]
        where:
          pairs    | expectedResult
          [[0, 0]] | [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    def 'Weighed Quick Union Example'() {
        setup:
          def sut = connectPairs(new WeighedQuickUnion(10), pairs)
        expect:
          sut.array == expectedResult as int[]
        where:
          pairs                                                                    | expectedResult
          [[8, 1], [4, 3], [6, 2], [4, 5], [7, 9], [5, 8], [7, 2], [6, 5], [8, 0]] | [4, 8, 6, 4, 4, 4, 7, 4, 4, 7]
          [[0, 2], [0, 9], [3, 8], [5, 1], [7, 0], [7, 6], [8, 5], [6, 5], [4, 7]] | [0, 5, 0, 0, 0, 3, 0, 0, 3, 0]
          [[7, 0], [3, 8], [9, 2], [6, 0], [2, 3], [7, 4], [7, 1], [7, 8], [3, 5]] | [7, 7, 9, 9, 7, 7, 7, 7, 3, 7]
    }

    def 'quick union'() {
        given:
          def unionFind = new QuickUnion(nextInt(0, 1000))
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
          def unionFind = new WeighedQuickUnion(nextInt(0, 1000))
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
          def unionFind = new WeighedQuickUnionWithPathCompression(nextInt(0, 1000))
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
