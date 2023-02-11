package com.demo.list.model.list;

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
    public void shouldKeepTrackOfSize() {
        list.add(100);
        list.add(200);
        assertEquals(2, list.size());
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
    public void indexOfWithMoreThanOneElement() {
        list.add(100);
        list.add(200);
        list.add(300);
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
    public void getWithMultipleElements() {
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
    }

    @Test
    public void shouldAddElementAtLastIndexWithoutThrowingException() {
        list.add(200);
        list.add(400);
        assertDoesNotThrow(() -> list.add(2, 600));
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
    public void addWithIndexOnListWithMultipleElements() {
        list.add(100);
        list.add(300);
        list.add(500);
        list.add(2, 400);
        assertEquals(list.get(2), 400);
    }

    @Test
    public void elementsMoveUpOnePositionAfterAddingWithIndex() {
        list.add(100);
        list.add(300);
        list.add(500);
        list.add(2, 400);
        assertEquals(list.get(3), 500);
    }

    @Test
    public void containsReturnsFalseOnEmptyList() {
        assertFalse(list.contains(100));
    }

    @Test
    public void containsReturnsTrueWhenAppropriate() {
        list.add(100);
        assertTrue(list.contains(100));
    }

    @Test
    public void containsReturnsFalseWhenAppropriate() {
        list.add(100);
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


}