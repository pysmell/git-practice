package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * 为名为13765的用户添加新的ACL条目
 */
public class Test46 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("D:\\", "test.txt");

        AclFileAttributeView aclFileAttributeView = Files.getFileAttributeView(path, AclFileAttributeView.class);

        if (aclFileAttributeView == null) {
            System.out.format("Acl view is not supported.%n");
            return;
        }

        UserPrincipal userPrincipal = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("13765");

        Set<AclEntryPermission> permissions = EnumSet.of(AclEntryPermission.READ_DATA, AclEntryPermission.WRITE_DATA);

        AclEntry.Builder builder = AclEntry.newBuilder();

        builder.setPrincipal(userPrincipal);

        builder.setType(AclEntryType.ALLOW);

        builder.setPermissions(permissions);

        AclEntry aclEntry = builder.build();

        List<AclEntry> aclEntries = aclFileAttributeView.getAcl();

        aclEntries.add(aclEntry);

        aclFileAttributeView.setAcl(aclEntries);
    }

}
