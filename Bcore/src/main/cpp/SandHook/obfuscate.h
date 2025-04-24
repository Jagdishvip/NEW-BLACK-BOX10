#include <cstddef>
#include <string>
namespace ay
{
	template <std::size_t N, char KEY>
	class obfuscator
	{
	public:	
		constexpr obfuscator(const char* data)
		{
			static_assert(KEY != '\0', "KEY must not be the null character.");		
			for (std::size_t i = 0; i < N; i++)
			{
				m_data[i] = data[i] ^ KEY;
			}
		}

		constexpr const char* getData() const
		{
			return &m_data[0];
		}

		constexpr std::size_t getSize() const
		{
			return N;
		}

		constexpr char getKey() const
		{
			return KEY;
		}

	private:

		char m_data[N]{};
	};

	template <std::size_t N, char KEY>
	class OBFUSCATE_data
	{
	public:
		OBFUSCATE_data(const obfuscator<N, KEY>& obfuscator)
		{
			for (std::size_t i = 0; i < N; i++)
			{
				m_data[i] = obfuscator.getData()[i];
			}
		}

		~OBFUSCATE_data()
		{
			// Zero m_data to remove it from memory
			for (std::size_t i = 0; i < N; i++)
			{
				m_data[i] = 0;
			}
		}

		operator char*()
		{
			decrypt();
			return m_data;
		}
		operator std::string()
		{
			decrypt();
			return m_data;
		}
		// Manually decrypt the string
		void decrypt()
		{
			if (is_encrypted())
			{
				for (std::size_t i = 0; i < N; i++)
				{
					m_data[i] ^= KEY;
				}
			}
		}

		// Manually re-encrypt the string
		void encrypt()
		{
			if (!is_encrypted())
			{
				for (std::size_t i = 0; i < N; i++)
				{
					m_data[i] ^= KEY;
				}
			}
		}

		// Returns true if this string is currently encrypted, false otherwise.
		bool is_encrypted() const
		{
			return m_data[N - 1] != '\0';
		}

	private:

		// Local storage for the string. Call is_encrypted() to check whether or
		// not the string is currently OBFUSCATE.
		char m_data[N];
	};

	// This function exists purely to extract the number of elements 'N' in the
	// array 'data'
	template <std::size_t N, char KEY = '.'>
	constexpr auto make_obfuscator(const char(&data)[N])
	{
		return obfuscator<N, KEY>(data);
	}
}


#define OBFUSCATE(data) OBFUSCATE_KEY(data, '.')
#define OBFUSCATE_KEY(data, key) \
	[]() -> ay::OBFUSCATE_data<sizeof(data)/sizeof(data[0]), key>& { \
		constexpr auto n = sizeof(data)/sizeof(data[0]); \
		static_assert(data[n - 1] == '\0', "String must be null terminated"); \
		constexpr auto obfuscator = ay::make_obfuscator<n, key>(data); \
		static auto OBFUSCATE_data = ay::OBFUSCATE_data<n, key>(obfuscator); \
		return OBFUSCATE_data; \
	}()
	
