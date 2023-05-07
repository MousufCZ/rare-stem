package com.mousuf.rarestem.projContributionSys.getContrib;


import java.util.List;
import org.junit.Assert;
import org.junit.Test;

class GetContribModelTest {

    @Test
    public void testGetAllContribs() {
        // Create a test instance of the class that contains the getAllContribs() method
        GetContribModel myClass = new GetContribModel();

        // Call the getAllContribs() method and save the result
        List<GetContrib> result = myClass.getAllContribs();

        // Verify that the result is not null and contains at least one element
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());

        // Verify that the first element of the result has the expected values
        GetContrib firstElement = result.get(0);
        Assert.assertEquals("Test Project 1", firstElement.getProjectName());
        Assert.assertEquals("Test Project 1 description", firstElement.getProjectDescription());
        Assert.assertEquals("Test Owner 1", firstElement.getProjectOwner());
        Assert.assertEquals("http://example.com/testproject1", firstElement.getProjectURL());
    }

}