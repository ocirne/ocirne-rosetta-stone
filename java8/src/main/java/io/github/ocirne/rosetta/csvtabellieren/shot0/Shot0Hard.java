package io.github.ocirne.rosetta.csvtabellieren.shot0;

import io.github.ocirne.rosetta.csvtabellieren.CsvTabulator;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;

/**
 * Als Basis für eine Refactoring Kata eine "historisch gewachsene" Variante.
 */
public class Shot0Hard implements CsvTabulator {

    @Override
    public List<String> tabelliere(List<String> d) {
        String[][]t=new
        String[d.size()][];for(int
        r=0;r<d.size();r++)t[r]=d.get(r).split(";");int[]m=new
        int[t[0].length];for(int
        i=0;i<t[0].length;i++)m[i]=0;for(String[]r:t)for(int
        i=0;i<r.length;i++)m[i]=max(m[i],r[i].length());String[]r=new
        String[t.length+1];String
        l="";for(int
        j=0;j<=t.length;j++){String[]c=t[j==0?0:j-1];for(int
        i=0;i<c.length;i++){l+=c[i];for(int
        k=0;k<m[i]-c[i].length();k++)l+=' ';l+='|';}r[j]=l;l="";if(j==0){j++;for(int
        i=0;i<c.length;i++){for(int
        k=0;k<m[i];k++)l+='-';l+='+';}r[j]=l;l="";}}
        return Arrays.asList(r);
    }
}
