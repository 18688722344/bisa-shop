package com.bisa.health.shop.admin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bisa.health.shop.admin.dto.AuthNode;

public class AuthTreeUtils {
	
	public static List<AuthNode> getInfiniteLevelTree() {
        // 读取层次数据结果集列表
		
        List<AuthNode> dataList = new ArrayList<AuthNode>();
        AuthNode _node = new AuthNode(1, "用户管理", "user-manager", -1, true);
        dataList.add(_node);
        _node = new AuthNode(2, "添加用户", "user-manager-add", 1, true);
        dataList.add(_node);
        _node = new AuthNode(3, "删除用户", "user-manager-del", 1, true);
        dataList.add(_node);
        _node = new AuthNode(4, "角色管理", "role-manager", -1, true);
        dataList.add(_node);
        _node = new AuthNode(5, "添加角色", "role-manager-add", 4, true);
        dataList.add(_node);
        
        // 将AuthNode存入散列表
        Map<Integer, AuthNode> nodeMap = dataList.stream().collect(Collectors.toMap(AuthNode::getId, node -> node));
        // 根节点
        List<AuthNode> root = new ArrayList<>();
        // 构造无序的多叉树
        for(Map.Entry<Integer, AuthNode> entry: nodeMap.entrySet()) {
            AuthNode node = entry.getValue();
            if (node.getParentId() == -1) {//根节点
                root.add(node);
            } else {	//子节点
                nodeMap.get(node.getParentId()).addChild(node);
            }
        }
        return root;
    }
}
