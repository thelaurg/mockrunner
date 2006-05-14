package com.mockrunner.gen.jar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.kirkk.analyzer.framework.Jar;

public class JarFileExtractor
{
    private List mainJars;
    private List exceptionJars;

    public JarFileExtractor(List mainJars, List exceptionJars)
    {
        this.mainJars = new ArrayList(mainJars);
        this.exceptionJars = new ArrayList(exceptionJars);
    }
    
    public Jar[] filterBundles(Jar jarBundles[])
    {
        List finalList = new ArrayList();
        for(int ii = 0; ii < jarBundles.length; ii++) 
        {
            if(mainJars.contains(jarBundles[ii].getJarFileName()))
            {
                finalList.add(jarBundles[ii]);
            }
        }
        return (Jar[])finalList.toArray(new Jar[finalList.size()]);
    }
    
    public Map createDependencies(Jar jarBundles[])
    {
        Map finalMap = new HashMap();
        for(int ii = 0; ii < jarBundles.length; ii++) 
        {
            if(mainJars.contains(jarBundles[ii].getJarFileName()))
            {
                Set currentSet = createDependencySet(jarBundles[ii]);
                finalMap.put(jarBundles[ii].getJarFileName(), currentSet);
            }
        }
        return finalMap;
    }
    
    private Set createDependencySet(Jar jarBundle)
    {
        Set resultSet = new TreeSet();
        List dependendJars = jarBundle.getOutgoingDependencies();
        if(null == dependendJars) return resultSet;
        for(int ii = 0; ii < dependendJars.size(); ii++)
        {
            Jar currentBundle = (Jar)dependendJars.get(ii);
            String currentJarFileName = currentBundle.getJarFileName();
            resultSet.add(currentJarFileName);
            if(!exceptionJars.contains(currentJarFileName))
            {
                resultSet.addAll(createDependencySet(currentBundle));
            }
        }
        return resultSet;
    }
}
