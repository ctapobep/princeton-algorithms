package princeton.union

import org.apache.commons.lang3.RandomUtils
import spock.lang.Specification

import static princeton.union.UnionFindProperties.isIdempotent
import static princeton.union.UnionFindProperties.isReflexive
import static princeton.union.UnionFindProperties.isSymmetric
import static princeton.union.UnionFindProperties.isTransitive

class UnionFindTest extends Specification {

    def 'eager union'() {
        given:
          def unionFind = new EagerUnionFind(RandomUtils.nextInt(1, 1000))
          def q = RandomUtils.nextInt(0, unionFind.size() - 1)
          def p = RandomUtils.nextInt(0, unionFind.size() - 1)
        when:
          unionFind.union(q, p)
        then:
          isReflexive(unionFind)
          isSymmetric(unionFind)
          isTransitive(unionFind)
          isIdempotent(unionFind)
    }

    def 'quick union'() {
        given:
          def unionFind = new QuickUnionFind(RandomUtils.nextInt(1, 1000))
          def q = RandomUtils.nextInt(0, unionFind.size() - 1)
          def p = RandomUtils.nextInt(0, unionFind.size() - 1)
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
          def unionFind = new WeighedQuickUnionFind(RandomUtils.nextInt(1, 1000))
          def q = RandomUtils.nextInt(0, unionFind.size() - 1)
          def p = RandomUtils.nextInt(0, unionFind.size() - 1)
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
          def unionFind = new WeighedQuickUnionFindWithPathCompression(RandomUtils.nextInt(1, 1000))
          def q = RandomUtils.nextInt(0, unionFind.size() - 1)
          def p = RandomUtils.nextInt(0, unionFind.size() - 1)
        when:
          unionFind.union(q, p)
        then:
          isReflexive(unionFind)
          isSymmetric(unionFind)
          isTransitive(unionFind)
          isIdempotent(unionFind)
    }
}
