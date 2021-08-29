package cn.cunchang.packagesacn;

import java.io.IOException;
import java.util.List;

public interface PackageScanner {

    List<String> getFullyQualifiedClassNameList() throws IOException;

}