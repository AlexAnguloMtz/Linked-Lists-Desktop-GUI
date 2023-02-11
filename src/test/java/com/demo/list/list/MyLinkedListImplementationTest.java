package com.demo.list.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListImplementationTest {

    private MyLinkedListImplementation<Integer> list;

    @BeforeEach
    void init() {
        this.list = new MyLinkedListImplementation<>();
    }

    @Test
    public void shouldBeEmptyAtCreationTime() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void shouldNotBeEmptyAfterAddingElements() {
        list.add(100);
        assertFalse(list.isEmpty());
    }

    @Test
    public void shouldKeepTrackOfSizeWithOneElement() {
        list.add(100);
        assertEquals(1, list.size());
    }

    @Test
    public void shouldKeepTrackOfSizeWithTwoElements() {
        list.add(100);
        list.add(200);
        assertEquals(2, list.size());
    }

    @Test
    public void shouldKeepTrackOfSizeWithMultipleElements() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(500);
        assertEquals(5, list.size());
    }

    @Test
    public void shouldReturnMinusOneWhenLookingForNonExistentElement() {
        assertEquals( -1, list.indexOf(300));
    }

    @Test
    public void indexOfWithOneElement() {
        list.add(300);
        assertEquals(0, list.indexOf(300));
    }

    @Test
    public void indexOfFirstElementWithTwoElements() {
        list.add(100);
        list.add(200);
        assertEquals(0, list.indexOf(100));
    }

    @Test
    public void indexOfLastElementWithTwoElements() {
        list.add(100);
        list.add(200);
        assertEquals(1, list.indexOf(200));
    }

    @Test
    public void indexOfFirstElementWithMultipleElements() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        assertEquals(0, list.indexOf(100));
    }

    @Test
    public void indexOfLastElementWithMultipleElements() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        assertEquals(3, list.indexOf(400));
    }

    @Test
    public void indexOfIntermediateElementWithMultipleElements() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        assertEquals(2, list.indexOf(300));
    }

    @Test
    public void shouldThrowExceptionWhenGettingElementOnEmptyList() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.get(0)
        );
    }

    @Test
    public void getWithOneElement() {
        list.add(300);
        assertEquals(300, list.get(0));
    }

    @Test
    public void getFirstWithWithTwoElements() {
        list.add(300);
        list.add(400);
        assertEquals(300, list.get(0));
    }

    @Test
    public void getLastWithWithTwoElements() {
        list.add(300);
        list.add(400);
        assertEquals(400, list.get(1));
    }

    @Test
    public void getFirstElementWithMultipleElements() {
        list.add(300);
        list.add(400);
        list.add(600);
        list.add(800);
        assertEquals(300, list.get(0));
    }

    @Test
    public void getIntermediateElementWithMultipleElements() {
        list.add(300);
        list.add(400);
        list.add(600);
        list.add(800);
        assertEquals(600, list.get(2));
    }

    @Test
    public void getLastElementWithMultipleElements() {
        list.add(300);
        list.add(400);
        list.add(600);
        list.add(800);
        assertEquals(800, list.get(3));
    }

    @Test
    public void addWithIndexOnEmptyList() {
        list.add(0, 100);
        assertEquals(list.get(0), 100);
    }

    @Test
    public void addWithIndexZeroShouldMoveTheFirstElement() {
        list.add(0, 100);
        list.add(0, 800);
        assertEquals(list.get(0), 800);
        assertEquals(list.get(1), 100);
    }

    @Test
    public void shouldAddElementAtLastIndex() {
        list.add(200);
        list.add(400);
        list.add(2, 800);
        assertEquals(800, list.get(2));
    }

    @Test
    public void shouldAddElementAtIntermediateIndex() {
        list.add(200);
        list.add(400);
        list.add(1, 300);
        assertEquals(300, list.get(1));
    }

    @Test
    public void allElementsMoveUpOneIndexAfterAddingNewFirstElement() {
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(0, 100);
        assertEquals(0, list.indexOf(100));
        assertEquals(1, list.indexOf(200));
        assertEquals(2, list.indexOf(300));
        assertEquals(3, list.indexOf(400));
    }

    @Test
    public void afterAddingElementAtFirstIndexReturnsSizeCorrectly() {
        list.add(200);
        list.add(400);
        list.add(0, 100);
        assertEquals(3, list.size());
    }

    @Test
    public void afterAddingElementAtLastIndexReturnsSizeCorrectly() {
        list.add(200);
        list.add(400);
        list.add(2, 600);
        assertEquals(3, list.size());
    }

    @Test
    public void afterAddingElementAtLastIndexContainsWorksCorrectly() {
        list.add(200);
        list.add(400);
        list.add(2, 600);
        assertTrue(list.contains(600));
    }

    @Test
    public void containsReturnsFalseOnEmptyList() {
        assertFalse(list.contains(100));
    }

    @Test
    public void containsReturnsTrueWhenAppropriate() {
        list.add(100);
        list.add(200);
        assertTrue(list.contains(100));
    }

    @Test
    public void containsReturnsFalseWhenAppropriate() {
        list.add(100);
        list.add(300);
        assertFalse(list.contains(200));
    }

    @Test
    public void shouldThrowExceptionWhenRemovingElementFromEmptyList() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> list.remove(0)
        );
    }

    @Test
    public void removesWithOneElement() {
        list.add(100);
        list.remove(0);
        assertEquals(0, list.size());
        assertFalse(list.contains(100));
    }

    @Test
    public void removesWithTwoElements() {
        list.add(100);
        list.add(200);
        list.remove(0);
        assertEquals(1, list.size());
        assertFalse(list.contains(100));
        assertTrue(list.contains(200));
    }

    @Test
    public void removesFirstElementWithMultipleElements() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(500);
        list.remove(0);
        assertEquals(4, list.size());
        assertFalse(list.contains(100));
    }

    @Test
    public void removesIntermediateElementWithMultipleElements() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(500);
        list.remove(3);
        assertEquals(4, list.size());
        assertFalse(list.contains(400));
    }

    @Test
    public void removesLastElementWithMultipleElements() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(500);
        list.remove(4);
        assertEquals(4, list.size());
        assertFalse(list.contains(500));
    }

    @Test
    public void shouldDoNothingWhenSortingEmptyList() {
        list.sort((x, y) -> x - y);
    }

    @Test
    public void shouldDoNothingWhenSortingListWithOneElement() {
        list.add(500);
        list.sort((x, y) -> x - y);
        assertEquals(0, list.indexOf(500));
    }

    @Test
    public void shouldSortListWithTwoElements() {
        list.add(500);
        list.add(300);
        list.sort((x, y) -> x - y);
        assertEquals(0, list.indexOf(300));
        assertEquals(1, list.indexOf(500));
    }

    @Test
    public void shouldSortListWithMultipleElements() {
        list.add(800);
        list.add(600);
        list.add(700);
        list.add(200);
        list.add(300);
        list.add(100);
        list.sort((x, y) -> x - y);
        assertEquals(0, list.indexOf(100));
        assertEquals(1, list.indexOf(200));
        assertEquals(2, list.indexOf(300));
        assertEquals(3, list.indexOf(600));
        assertEquals(4, list.indexOf(700));
        assertEquals(5, list.indexOf(800));
    }

    @Test
    void shouldReturnTrueOnEmptyList() {
        assertTrue(list.isSorted((x, y) -> (x - y) < 0));
    }

    @Test
    void shouldReturnTrueWithOneElement() {
        list.add(100);
        assertTrue(list.isSorted((x, y) -> (x - y) < 0));
    }

    @Test
    void shouldReturnTrueWithTwoSortedElements() {
        list.add(100);
        list.add(200);
        assertTrue(list.isSorted((x, y) -> (x - y) < 0));
    }

    @Test
    void shouldReturnTrueWithMultipleSortedElements() {
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        assertTrue(list.isSorted((x, y) -> (x - y) < 0));
    }

    @Test
    void isSortedReturnsTrueWithAllElementsTheSame() {
        list.add(100);
        list.add(100);
        list.add(100);
        assertTrue(list.isSorted((x, y) -> (x - y) >= 0));
    }

    @Test
    public void shouldReturnFalseWithTwoUnsortedElements() {
        list.add(200);
        list.add(100);
        assertFalse(list.isSorted((x, y) -> (x - y) < 0));
    }

    @Test
    public void shouldReturnFalseWithMultipleUnsortedElements() {
        list.add(200);
        list.add(700);
        list.add(500);
        list.add(300);
        assertFalse(list.isSorted((x, y) -> (x - y) < 0));
    }

    @Test
    void allEqualReturnsTrueOnEmptyList() {
        assertTrue(list.allEqual());
    }

    @Test
    void allEqualReturnsTrueWithOneElement() {
        list.add(700);
        assertTrue(list.allEqual());
    }

    @Test
    void allEqualReturnsTrueWithTwoEqualElements() {
        list.add(700);
        list.add(700);
        assertTrue(list.allEqual());
    }

    @Test
    void allEqualReturnsTrueWithMultipleEqualElements() {
        list.add(700);
        list.add(700);
        list.add(700);
        list.add(700);
        assertTrue(list.allEqual());
    }

    @Test
    void allEqualReturnsFalseWhenNotAllElementsAreEqual() {
        list.add(600);
        list.add(700);
        assertFalse(list.allEqual());
    }


}