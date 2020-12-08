package com.example.stompcovidrumors

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TestValidation {
    // TODO: Test passwords
    // Add two tests for a valid password

    // test for invalid password
    @Test
    fun testInvalidPassword1() {
        val validator = Validators()
        assertFalse(validator.validPassword("To"))
        assertFalse(validator.validPassword("Chi1"))
        assertFalse(validator.validPassword("Glo"))
    }
    //test invalid password continued
    @Test
    fun testInvalidPassword2() {
        val validator = Validators()
        assertFalse(validator.validPassword("Path"))
        assertFalse(validator.validPassword("X"))
        assertFalse(validator.validPassword("2352"))
    }
    // Add two tests for an invalid password

    // test valid password
    @Test
    fun testValidPassword1() {
        val validator = Validators()
        assertTrue(validator.validPassword("GhASt2"))
        assertTrue(validator.validPassword("Gox3"))
        assertTrue(validator.validPassword("Friend44"))
    }
    //test valid password continued
    @Test
    fun testValidPassword2() {
        val validator = Validators()
        assertTrue(validator.validPassword("Mzsboy13"))
        assertTrue(validator.validPassword("shae9"))
        assertTrue(validator.validPassword("bog6"))
    }
}