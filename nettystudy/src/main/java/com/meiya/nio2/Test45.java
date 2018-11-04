package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclFileAttributeView;
import java.util.List;
import java.util.Set;

/**
 * ACL文件权限,Windows下支持
 */
public class Test45 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("d:\\", "test.txt");

        AclFileAttributeView aclFileAttributeView = Files.getFileAttributeView(path, AclFileAttributeView.class);

        if (aclFileAttributeView == null) {
            System.out.format("ACL view is not supported.%n");
            return;
        }

        List<AclEntry> aclEntries = aclFileAttributeView.getAcl();

        for (AclEntry aclEntry : aclEntries) {
            //用户或组
            System.out.format("Principal: %s%n", aclEntry.principal());
            //访问类型
            System.out.format("Type: %s%n", aclEntry.type());
            //文件权限
            System.out.format("Permissions are: %n");

            Set<AclEntryPermission> permissions = aclEntry.permissions();

            for (AclEntryPermission aclEntryPermission : permissions) {
                System.out.format("%s %n", aclEntryPermission);
            }
        }
    }

}
