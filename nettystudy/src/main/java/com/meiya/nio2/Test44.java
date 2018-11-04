package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;

/**
 * 文件所有者权限
 */
public class Test44 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("C:\\Users\\13765\\Desktop", "java运行Linux命令.txt");

        FileOwnerAttributeView fileOwnerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);

        UserPrincipal owner = fileOwnerAttributeView.getOwner();

        System.out.format("Original owner of %s is %s%n", path, owner.getName());

        FileSystem fileSystems = FileSystems.getDefault();

        UserPrincipalLookupService userPrincipalLookupService = fileSystems.getUserPrincipalLookupService();

        UserPrincipal newOwner = userPrincipalLookupService.lookupPrincipalByName("13765");

        fileOwnerAttributeView.setOwner(newOwner);

        UserPrincipal changedOwner = fileOwnerAttributeView.getOwner();

        System.out.format("New owner of %s is %s%n", path, changedOwner.getName());
    }

}

/**
 * 有三种方法管理文件所有者
 * 使用Files.getOwner()和Files.setOwner()方法。
 * 使用“owner”作为属性名称的Files.getAttribute()和Files.setAttribute()方法。
 * 使用FileOwnerAttributeView。
 * 我们需要使用UserPrincipal和GroupPrincipal接口来管理文件的所有者。
 * 文件的所有者可以是用户或组。
 * UserPrincipal表示用户。GroupPrincipal表示组。
 * 当我们读取文件的所有者时，我们得到一个UserPrincipal的实例。在UserPrincipal对象上调用getName()方法以获取用户的名称。
 * 要设置文件的所有者，请从用户名获取UserPrincipal的对象。
 * 要从文件系统获取UserPrincipal，请使用UserPrincipalLookupService类的实例，
 * 我们可以使用FileSystem类的getUserPrincipalLookupService()方法获取该实例。
 */































































































