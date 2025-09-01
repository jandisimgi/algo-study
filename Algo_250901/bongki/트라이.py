"""
삼성 B형 스타일 패턴 매칭 문제

문제: 고급 문서 검색 시스템

회사에서 문서 검색 시스템을 개발하고 있다. 
이 시스템은 다음과 같은 복잡한 패턴 매칭을 지원해야 한다:

1. 일반 문자열 검색
2. 와일드카드 검색 ('?' = 임의의 한 글자, '*' = 임의의 0개 이상 글자)
3. 접두사 검색 (prefix$로 시작하는 모든 단어)
4. 접미사 검색 ($suffix로 끝나는 모든 단어)
5. 부분 문자열 검색 (contain을 포함하는 모든 단어)
6. 정규식 패턴 (단순한 형태만)

제한사항:
- 문서 수: 최대 100,000개
- 각 문서의 단어 수: 최대 1,000개
- 쿼리 수: 최대 10,000개
- 문자열 길이: 최대 50자
- 시간 제한: 2초

입력:
- 첫 줄: 문서 수 N, 쿼리 수 Q
- 다음 N개 줄: 각 문서의 내용 (공백으로 구분된 단어들)
- 다음 Q개 줄: 검색 쿼리

출력:
- 각 쿼리에 대해 매치되는 문서의 개수
"""

import re
from collections import defaultdict


class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end = False
        self.doc_ids = set()  # 이 노드를 포함하는 문서 ID들


class AdvancedPatternMatcher:
    """삼성 B형 스타일의 고급 패턴 매칭 시스템"""
    
    def __init__(self):
        self.prefix_trie = TrieNode()  # 접두사 검색용
        self.suffix_trie = TrieNode()  # 접미사 검색용 (뒤집어서 저장)
        self.documents = []           # 원본 문서들
        self.word_to_docs = defaultdict(set)  # 단어 -> 문서 ID 매핑
        self.doc_words = defaultdict(set)     # 문서 ID -> 단어들 매핑
    
    def add_document(self, doc_id, content):
        """문서를 시스템에 추가"""
        words = content.lower().split()
        self.documents.append(content)
        
        for word in words:
            # 전체 단어 인덱싱
            self.word_to_docs[word].add(doc_id)
            self.doc_words[doc_id].add(word)
            
            # 접두사 트라이에 추가
            self._add_to_trie(self.prefix_trie, word, doc_id)
            
            # 접미사 트라이에 추가 (뒤집어서)
            self._add_to_trie(self.suffix_trie, word[::-1], doc_id)
    
    def _add_to_trie(self, root, word, doc_id):
        """트라이에 단어 추가"""
        current = root
        for char in word:
            if char not in current.children:
                current.children[char] = TrieNode()
            current = current.children[char]
            current.doc_ids.add(doc_id)
        current.is_end = True
    
    def _search_prefix_trie(self, root, pattern):
        """트라이에서 패턴 검색"""
        current = root
        for char in pattern:
            if char not in current.children:
                return set()
            current = current.children[char]
        return current.doc_ids
    
    def search(self, query):
        """쿼리에 따른 검색 수행"""
        query = query.strip().lower()
        
        # 1. 접두사 검색 (pattern$)
        if query.endswith('$') and not query.startswith('$'):
            prefix = query[:-1]
            return self._search_prefix_trie(self.prefix_trie, prefix)
        
        # 2. 접미사 검색 ($pattern)
        elif query.startswith('$') and not query.endswith('$'):
            suffix = query[1:]
            return self._search_prefix_trie(self.suffix_trie, suffix[::-1])
        
        # 3. 부분 문자열 검색 (contain)
        elif not ('*' in query or '?' in query):
            result = set()
            for doc_id, words in self.doc_words.items():
                for word in words:
                    if query in word:
                        result.add(doc_id)
                        break
            return result
        
        # 4. 와일드카드 검색 (*, ?)
        else:
            return self._wildcard_search(query)
    
    def _wildcard_search(self, pattern):
        """와일드카드 패턴 검색"""
        result = set()
        
        # 패턴을 정규식으로 변환
        regex_pattern = pattern.replace('?', '.').replace('*', '.*')
        regex_pattern = '^' + regex_pattern + '$'
        
        try:
            compiled_pattern = re.compile(regex_pattern)
            for doc_id, words in self.doc_words.items():
                for word in words:
                    if compiled_pattern.match(word):
                        result.add(doc_id)
                        break
        except:
            return set()
        
        return result
    
    def advanced_search(self, query):
        """고급 검색 (복합 조건)"""
        query = query.strip().lower()
        
        # AND 조건 처리
        if ' and ' in query:
            parts = query.split(' and ')
            result = None
            for part in parts:
                part_result = self.search(part.strip())
                if result is None:
                    result = part_result
                else:
                    result = result.intersection(part_result)
            return result if result else set()
        
        # OR 조건 처리
        elif ' or ' in query:
            parts = query.split(' or ')
            result = set()
            for part in parts:
                result = result.union(self.search(part.strip()))
            return result
        
        # 단일 조건
        else:
            return self.search(query)


def solve():
    """메인 솔루션 함수"""
    # 입력 처리
    n, q = map(int, input().split())
    
    matcher = AdvancedPatternMatcher()
    
    # 문서 추가
    for i in range(n):
        content = input().strip()
        matcher.add_document(i, content)
    
    # 쿼리 처리
    for _ in range(q):
        query = input().strip()
        result = matcher.advanced_search(query)
        print(len(result))


# 테스트 케이스 실행을 위한 함수
def run_test():
    """테스트 케이스 실행"""
    print("=== 삼성 B형 스타일 패턴 매칭 테스트 ===")
    
    matcher = AdvancedPatternMatcher()
    
    # 테스트 문서들
    documents = [
        "apple banana cherry",
        "application programming interface",
        "banana split dessert",
        "cherry blossom season",
        "apple pie recipe",
        "programming language python",
        "interface design patterns"
    ]
    
    for i, doc in enumerate(documents):
        matcher.add_document(i, doc)
    
    # 테스트 쿼리들
    test_queries = [
        "apple",           # 일반 검색
        "app$",           # 접두사 검색
        "$ing",           # 접미사 검색
        "prog",           # 부분 문자열 검색
        "a*e",            # 와일드카드 검색
        "?pple",          # 와일드카드 검색
        "apple or banana", # OR 검색
        "programming and python"  # AND 검색
    ]
    
    print("문서 목록:")
    for i, doc in enumerate(documents):
        print(f"{i}: {doc}")
    
    print("\n검색 결과:")
    for query in test_queries:
        result = matcher.advanced_search(query)
        print(f"Query: '{query}' -> 매칭 문서 수: {len(result)}, 문서 ID: {sorted(result)}")
    
    print("\n=== 성능 테스트 ===")
    import time
    
    # 대량 데이터로 성능 테스트
    large_matcher = AdvancedPatternMatcher()
    
    # 10,000개의 가상 문서 생성
    import random
    words = ["apple", "banana", "cherry", "date", "elderberry", "fig", "grape",
             "programming", "computer", "algorithm", "data", "structure", "design",
             "pattern", "system", "network", "database", "interface"]
    
    start_time = time.time()
    for i in range(10000):
        doc_content = " ".join(random.choices(words, k=random.randint(5, 20)))
        large_matcher.add_document(i, doc_content)
    
    build_time = time.time() - start_time
    print(f"10,000개 문서 인덱싱 시간: {build_time:.3f}초")
    
    # 검색 성능 테스트
    search_queries = ["apple", "prog$", "$ing", "a*e", "computer or network"]
    
    start_time = time.time()
    for query in search_queries * 1000:  # 5,000회 검색
        result = large_matcher.advanced_search(query)
    
    search_time = time.time() - start_time
    print(f"5,000회 검색 시간: {search_time:.3f}초")
    print(f"평균 검색 시간: {search_time/5000*1000:.3f}ms")


if __name__ == "__main__":
    # 실제 제출용 코드는 solve() 함수 호출
    # solve()
    
    # 테스트 실행
    run_test()


"""
삼성 B형 출제 스타일 분석:

1. 복잡한 자료구조 조합
   - Trie + HashMap + Set 조합 활용
   - 다중 인덱싱 구조

2. 다양한 패턴 매칭 요구사항
   - 와일드카드, 정규식, 접두사/접미사 등
   - 복합 조건 (AND, OR)

3. 성능 최적화 필요
   - 대용량 데이터 처리
   - 빠른 검색 속도 요구

4. 실제 적용 가능한 문제
   - 검색 엔진, 로그 분석 등
   - 실무와 연계된 복합 문제

핵심 알고리즘:
- Trie: O(L) 접두사/접미사 검색
- HashMap: O(1) 평균 검색
- 정규식: 와일드카드 처리
- 집합 연산: AND/OR 조건 처리

시간복잡도:
- 인덱싱: O(N × M × L) - N:문서수, M:문서당단어수, L:단어길이
- 검색: O(L + R) - L:패턴길이, R:결과크기
"""
