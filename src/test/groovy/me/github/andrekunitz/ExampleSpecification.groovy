package me.github.andrekunitz

import me.github.andre.kunitz.Polygon
import me.github.andre.kunitz.TooFewSidesException
import spock.lang.Specification

class ExampleSpecification extends Specification {

    def "should be a simple assertion"() {
        expect:
        1 == 1
    }

    def "should demonstrate given-when-then"() {
        when:
        int sides = new Polygon(4).numberOfSides

        then:
        sides == 4
    }

    def "should expect Exceptions"() {
        when:
        new Polygon(0)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == 0
    }

    def "should expect an Exception for invalid input: #sides"() {
        when:
        new Polygon(sides)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == sides

        where:
        sides << [-1, 0, 1, 2]
    }

    def "should be able to create a polygon with #sides sides verbose"() {
        when:
        def polygon = new Polygon(sides)

        then:
        polygon.numberOfSides == sides

        where:
        sides << [3, 4, 5, 14]
    }

    def "should be able to create a polygon with #sides sides"() {
        expect:
        new Polygon(sides).numberOfSides == sides

        where:
        sides << [3, 4, 5, 14]
    }
}
