package cn.lastwhisper.feature5.packagesacn;

import java.io.IOException;
import java.util.List;
public interface PackageScanner {
    public List<String> getFullyQualifiedClassNameList() throws IOException;
}