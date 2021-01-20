package me.github.andrekunitz

import me.github.andre.kunitz.Colour
import me.github.andre.kunitz.Palette
import me.github.andre.kunitz.Polygon
import me.github.andre.kunitz.Renderer
import me.github.andre.kunitz.ShapeFactory
import me.github.andre.kunitz.TooFewSidesException
import spock.lang.Specification
import spock.lang.Subject

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

    def "should use data tables for calculating max"() {
        expect:
        Math.max(a , b) == max

        where:
        a | b || max
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    def "should be able to mock a concrete class"() {
        given:
        // def renderer = Mock(Renderer)
        Renderer renderer = Mock()
        @Subject
        def polygon = new Polygon(4, renderer)

        when:
        polygon.draw()

        then:
        4 * renderer.drawLine()
    }

    def "should be able to create a stub"() {
        given:
        Palette palette = Stub()
        palette.getPrimaryColour() >> Colour.Red
        @Subject
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegroundColour() == Colour.Red
    }

    def "should use a helper method"() {
        given:
        Renderer renderer = Mock()
        ShapeFactory shapeFactory = new ShapeFactory(renderer)

        when:
        def shape = shapeFactory.createDefaultPolygon()

        then:
        checkDefaultShape(shape, renderer)
    }

    private static void checkDefaultShape(Polygon shape, Renderer renderer) {
        assert shape.numberOfSides == 4
        assert shape.renderer == renderer
    }

    def "should demonstrate 'with'"() {
        given:
        def renderer = Mock(Renderer)
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def shape = shapeFactory.createDefaultPolygon()

        then:
        with(shape) {
            numberOfSides == 4
            renderer == renderer
        }
    }

    def "should demonstrate 'verifyAll'"() {
        given:
        def renderer = Mock(Renderer)
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def shape = shapeFactory.createDefaultPolygon()

        then:
        verifyAll(shape) {
            numberOfSides == 4
            renderer == renderer
        }
        verifyAll {
            2 == 2
            4 == 4
        }
    }

    def "should show specification as documentation"() {
        given: "a palette with red as the primary colour"
        Palette palette = Stub()
        palette.getPrimaryColour() >> Colour.Red

        and: "a renderer initialised with the red palette"
        def renderer = new Renderer(palette)

        expect: "the renderer uses the palette's primary colour as the foreground colour"
        renderer.getForegroundColour() == Colour.Red
    }
}
