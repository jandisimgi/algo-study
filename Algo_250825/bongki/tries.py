# 딕셔너리로 간단한 트라이 구현

def insert(trie, word):
    """단어 삽입"""
    for char in word:
        if char not in trie:
            trie[char] = {}
        trie = trie[char]
    trie['*'] = True  # 단어 끝 표시

def search(trie, word):
    """단어 검색"""
    for char in word:
        if char not in trie:
            return False
        trie = trie[char]
    return '*' in trie

def starts_with(trie, prefix):
    """접두사 검색"""
    for char in prefix:
        if char not in trie:
            return False
        trie = trie[char]
    return True

def get_words_with_prefix(trie, prefix):
    """접두사로 시작하는 모든 단어 찾기"""
    result = []
    
    # 접두사까지 이동
    current = trie
    for char in prefix:
        if char not in current:
            return result
        current = current[char]
    
    # DFS로 모든 단어 수집
    def dfs(node, word):
        if '*' in node:
            result.append(word)
        for char, child in node.items():
            if char != '*':
                dfs(child, word + char)
    
    dfs(current, prefix)
    return result


# 사용 예시
if __name__ == "__main__":
    # 빈 트라이 생성 (그냥 딕셔너리)
    trie = {}
    
    # 단어들 삽입
    words = ["cat", "car", "card", "care", "careful", "dog", "dodge"]
    for word in words:
        insert(trie, word)
    
    print("트라이 구조:", trie)
    print()
    
    # 검색 테스트
    print("=== 검색 테스트 ===")
    print("'car' 있나?", search(trie, "car"))      # True
    print("'ca' 있나?", search(trie, "ca"))        # False (접두사일 뿐)
    print("'card' 있나?", search(trie, "card"))    # True
    
    print("\n=== 접두사 테스트 ===")
    print("'car'로 시작?", starts_with(trie, "car"))    # True
    print("'xyz'로 시작?", starts_with(trie, "xyz"))    # False
    
    print("\n=== 자동완성 ===")
    print("'car'로 시작하는 단어들:", get_words_with_prefix(trie, "car"))
    print("'do'로 시작하는 단어들:", get_words_with_prefix(trie, "do"))
